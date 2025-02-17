import os

from dotenv import load_dotenv
from flask import Flask

from news_feed.extensions import db
from news_feed.posts.controller import bp as post_blueprint

load_dotenv()


def create_app():
    app = Flask(__name__)
    app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv('DATABASE_URL')

    db.init_app(app)  # Initialize db with the app
    app.register_blueprint(post_blueprint)
    return app
