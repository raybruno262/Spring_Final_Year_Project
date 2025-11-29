from flask import Flask, request, jsonify
from flask_cors import CORS
import pickle
import pandas as pd
import logging
from datetime import datetime

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

app = Flask(__name__)
CORS(app)

# Global variables for models
category_model = None
priority_model = None
vectorizer = None

def load_models():
    """Load trained models and vectorizer"""
    global category_model, priority_model, vectorizer
    
    try:
        with open('models/category_model.pkl', 'rb') as f:
            category_model = pickle.load(f)
        
        with open('models/priority_model.pkl', 'rb') as f:
            priority_model = pickle.load(f)
        
        with open('models/vectorizer.pkl', 'rb') as f:
            vectorizer = pickle.load(f)
        
        logger.info("✓ Category model loaded")
        logger.info("✓ Priority model loaded")
        logger.info("✓ Vectorizer loaded")
        return True
        
    except Exception as e:
        logger.error(f"Error loading models: {e}")
        return False

@app.route('/health', methods=['GET'])
def health_check():
    """Health check endpoint"""
    models_loaded = all([category_model, priority_model, vectorizer])
    return jsonify({
        'status': 'healthy' if models_loaded else 'degraded',
        'service': 'NLP Classification Service',
        'models_loaded': models_loaded,
        'timestamp': datetime.now().isoformat()
    })

@app.route('/classify', methods=['POST'])
def classify_feedback():
    """Classify feedback text into category and priority"""
    try:
        data = request.get_json()
        text = data.get('text', '').strip()
        
        if not text:
            return jsonify({
                'error': 'No text provided'
            }), 400
        
        logger.info(f"Classifying text: {text[:100]}...")
        
        # Transform text using the vectorizer
        X = vectorizer.transform([text])
        
        # Predict category and priority
        category_pred = category_model.predict(X)[0]
        priority_pred = priority_model.predict(X)[0]
        
        # Get prediction probabilities
        category_proba = category_model.predict_proba(X)[0]
        priority_proba = priority_model.predict_proba(X)[0]
        
        # Get confidence scores
        category_confidence = max(category_proba)
        priority_confidence = max(priority_proba)
        
        result = {
            'category': category_pred,
            'priority': priority_pred,
            'category_confidence': round(float(category_confidence), 4),
            'priority_confidence': round(float(priority_confidence), 4)
        }
        
        logger.info(f"Classification result: {result}")
        return jsonify(result)
        
    except Exception as e:
        logger.error(f"Classification error: {e}")
        return jsonify({
            'error': 'Classification failed',
            'message': str(e)
        }), 500

@app.route('/models/status', methods=['GET'])
def model_status():
    """Check model status and information"""
    models_loaded = all([category_model, priority_model, vectorizer])
    
    status = {
        'models_loaded': models_loaded,
        'category_model_loaded': category_model is not None,
        'priority_model_loaded': priority_model is not None,
        'vectorizer_loaded': vectorizer is not None
    }
    
    return jsonify(status)

if __name__ == '__main__':
    # Load models when starting the application
    logger.info("Loading NLP models...")
    success = load_models()
    
    if success:
        logger.info("=" * 50)
        logger.info("All models loaded successfully!")
        logger.info("=" * 50)
        app.run(host='0.0.0.0', port=5000, debug=False)
    else:
        logger.error("Failed to load models. Exiting.")
