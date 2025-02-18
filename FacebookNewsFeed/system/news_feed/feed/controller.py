from typing import List

from flask import Blueprint, request, jsonify

from news_feed.extensions import db
from news_feed.feed.feed import Feed
from news_feed.follow.follow import Follow
from news_feed.posts.post import Post
from news_feed.user.user import User

bp = Blueprint('feed', __name__, url_prefix='/api/feed')

THRESHOLD = 200


@bp.route("/", methods=["GET"])
def get_feed():
    def user_exists(id: int) -> bool:
        return db.session.query(User.id).filter_by(id=id).first() is not None

    def get_posts(following: List[Follow]):
        def should_get_from_feed(following: List[Follow]) -> bool:
            return len(following) > THRESHOLD

        if should_get_from_feed(following):
            return db.session.query(Feed.user_id).filter_by(user_id=id).first()
        followee_ids = [
            follow[0].split(':')[1] for follow in following
        ]
        # Get all posts from followed users
        return db.session.query(Post).filter(
            Post.user_id.in_(followee_ids)
        ).order_by(Post.created_at.desc()).all()

    user_id = request.args.get(
        'id')  # this is a bad practice and only used in a small scale as an example should come from a header or session id
    if not user_exists(int(user_id)):
        return jsonify({"success": False, "data": f"User {user_id} does not exist"}), 404
    following = db.session.query(Follow.id).filter(
        Follow.id.like(f"{user_id}:%")
    ).all()
    return jsonify({"success": True, "data": [{
        'id': post.id,
        'content': post.content,
    } for post in get_posts(following)]}), 200
