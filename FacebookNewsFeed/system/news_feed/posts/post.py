from datetime import datetime

from news_feed import db


class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(100), nullable=False)
    date_posted = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    content = db.Column(db.Text, nullable=False)
    author = db.Column(db.Text, nullable=False)
    user_id = db.Column(db.Integer, nullable=False)
    feed_id = db.Column(db.Integer, db.ForeignKey('feed.id'), nullable=True)

    def get_id(self):
        return self.id

    def __repr__(self):
        return f"Post('{self.title}', '{self.date_posted}')"
