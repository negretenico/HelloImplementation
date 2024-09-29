from constants.constants import QUEUE_LIMIT
from models.queue import QueueInterface
import logging


def injest(queue:QueueInterface):
    for _ in QUEUE_LIMIT:
        try:
            queue.dequeue()
        except Exception:
            #It would be better to differentiate the error and handle each one differently
            logging.error("we encountered an error")