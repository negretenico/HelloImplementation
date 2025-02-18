from datetime import datetime

from news_feed.extensions import db


class Follow(db.Model):
    id = db.Column(db.Text, primary_key=True)
    date_followed = db.Column(db.DateTime, nullable=True, default=datetime.utcnow)
