from news_feed.extensions import db


class Feed(db.Model):
    id = db.Column(db.Integer, primary_key=True, nullable=False)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    posts = db.relationship('Post', backref='feed', lazy=True)
