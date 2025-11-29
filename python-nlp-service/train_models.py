import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, classification_report
import pickle
import os
import logging

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

def train_models():
    """Train category and priority classification models"""
    
    print("=" * 60)
    print("NLP MODEL TRAINING PIPELINE")
    print("=" * 60)
    
    # Step 1: Load and prepare data
    logger.info("Step 1: Loading training data...")
    try:
        df = pd.read_csv('training_data/feedback_training.csv')
        logger.info(f"✓ Loaded {len(df)} training samples")
    except Exception as e:
        logger.error(f"Error loading training data: {e}")
        return False
    
    # Step 2: Preprocess data
    logger.info("Step 2: Preprocessing data...")
    df = df.dropna()
    df['message'] = df['message'].str.lower().str.strip()
    
    # Step 3: Feature extraction
    logger.info("Step 3: Creating TF-IDF features...")
    vectorizer = TfidfVectorizer(
        max_features=1000,
        stop_words='english',
        ngram_range=(1, 2)
    )
    
    X = vectorizer.fit_transform(df['message'])
    y_category = df['category']
    y_priority = df['priority']
    
    # Step 4: Split data
    logger.info("Step 4: Splitting training and test data...")
    X_train, X_test, y_cat_train, y_cat_test, y_pri_train, y_pri_test = train_test_split(
        X, y_category, y_priority, test_size=0.2, random_state=42
    )
    
    # Step 5: Train category classifier
    logger.info("Step 5: Training category classifier...")
    category_model = RandomForestClassifier(
        n_estimators=100,
        random_state=42,
        max_depth=10
    )
    category_model.fit(X_train, y_cat_train)
    
    # Step 6: Train priority classifier
    logger.info("Step 6: Training priority classifier...")
    priority_model = RandomForestClassifier(
        n_estimators=100,
        random_state=42,
        max_depth=8
    )
    priority_model.fit(X_train, y_pri_train)
    
    # Step 7: Evaluate models
    logger.info("Step 7: Evaluating model performance...")
    
    # Category model evaluation
    y_cat_pred = category_model.predict(X_test)
    cat_accuracy = accuracy_score(y_cat_test, y_cat_pred)
    
    # Priority model evaluation
    y_pri_pred = priority_model.predict(X_test)
    pri_accuracy = accuracy_score(y_pri_test, y_pri_pred)
    
    print(f"\n📊 MODEL PERFORMANCE:")
    print(f"   Category Classification Accuracy: {cat_accuracy:.2%}")
    print(f"   Priority Classification Accuracy: {pri_accuracy:.2%}")
    
    # Step 8: Save models
    logger.info("Step 8: Saving trained models...")
    
    # Create models directory if it doesn't exist
    os.makedirs('models', exist_ok=True)
    
    # Save models and vectorizer
    with open('models/category_model.pkl', 'wb') as f:
        pickle.dump(category_model, f)
    
    with open('models/priority_model.pkl', 'wb') as f:
        pickle.dump(priority_model, f)
    
    with open('models/vectorizer.pkl', 'wb') as f:
        pickle.dump(vectorizer, f)
    
    logger.info("✓ Models saved successfully!")
    
    # Step 9: Show detailed classification report
    print(f"\n📋 DETAILED CATEGORY REPORT:")
    print(classification_report(y_cat_test, y_cat_pred))
    
    print(f"\n📋 DETAILED PRIORITY REPORT:")
    print(classification_report(y_pri_test, y_pri_pred))
    
    print("=" * 60)
    print("✓ TRAINING COMPLETED SUCCESSFULLY!")
    print("=" * 60)
    
    return True

if __name__ == '__main__':
    success = train_models()
    if not success:
        print("❌ Training failed. Check the logs above for details.")
        exit(1)