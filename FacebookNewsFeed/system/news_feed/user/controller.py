from flask import Blueprint, request, jsonify

from news_feed.extensions import db
from news_feed.user.user import User

bp = Blueprint('user', __name__, url_prefix='/api/user')


@bp.route("/", methods=["POST"])
def create():
    name = request.json['name']
    new_user = User(name=name)
    db.session.add(new_user)
    db.session.commit()
    return jsonify({"success": True, "data": new_user.id}), 201
