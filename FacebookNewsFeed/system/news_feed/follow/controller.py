from flask import Blueprint, request, jsonify

from news_feed.extensions import db
from news_feed.follow.follow import Follow
from news_feed.user.user import User

bp = Blueprint('follow', __name__, url_prefix='/api/follow')


@bp.route("/", methods=["POST"])
def create():
    def user_exists(id: int) -> bool:
        return db.session.query(User.id).filter_by(id=id).first() is not None

    follower = request.json['follower']  # person who wants to follow ie. John Smith
    if not user_exists(follower):
        return jsonify({"success": False, "data": f"User {follower} does not exist"}), 404
    followee = request.json['followee']  # person who is being followed ie. Islam Makhachev
    if not user_exists(followee):
        return jsonify({"success": False, "data": f"User {followee} does not exist"}), 404
    new_follow = Follow(id=f"{follower}:{followee}")
    db.session.add(new_follow)
    db.session.commit()
    return jsonify({"success": True}), 201
