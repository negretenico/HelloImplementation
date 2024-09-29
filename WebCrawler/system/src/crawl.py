from src.queue_mapper import get_queue
from src.fetch import fetch


def crawl():
    queue = get_queue()
    while queue.size():
        msg = queue.dequeue()
        html = fetch(msg)
        