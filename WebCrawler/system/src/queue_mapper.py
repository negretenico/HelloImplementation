import os
from src.models import CloudQueue
from src.cloud.SQSClient import SQSClient
from src.models.LocalQueue import LocalQueue
from src.models.queue import QueueInterface
def get_queue() -> QueueInterface:
    profile = os.getenv('profile', 'local')
    mapping = {
        'prod': CloudQueue(SQSClient('foo.com')),
        'local':LocalQueue()
    }
    return mapping.get(profile, LocalQueue())