from datetime import datetime

from flask import Blueprint, request, jsonify

from news_feed.extensions import db
from news_feed.posts.post import Post  # Ensure model is properly imported

bp = Blueprint('post', __name__, url_prefix='/api/post')


@bp.route("/", methods=["POST"])
def create():
    content = request.json['content']
    author = request.json['author']
    timestamp = request.json['timestamp']
    title = request.json['title']
    try:
        date_posted = datetime.strptime(timestamp, "%m/%d/%Y")  # Convert to datetime object
    except ValueError:
        return jsonify({"error": "Invalid timestamp format. Expected MM/DD/YYYY"}), 400
    new_post = Post(content=content, author=author, date_posted=date_posted, title=title)
    db.session.add(new_post)
    db.session.commit()
    return jsonify({"id": new_post.get_id()}), 201
