import os

from dotenv import load_dotenv
from flask import Flask

from news_feed.extensions import db
from news_feed.follow.controller import bp as follow_blueprint
from news_feed.posts.controller import bp as post_blueprint
from news_feed.user.controller import bp as user_blueprint

load_dotenv()


def create_app():
    app = Flask(__name__)
    app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv('DATABASE_URL')

    db.init_app(app)  # Initialize db with the app
    app.register_blueprint(post_blueprint)
    app.register_blueprint(follow_blueprint)
    app.register_blueprint(user_blueprint)
    return app
