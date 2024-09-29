from src.models.saver.saver import Saver
import os
from src.models.saver import LocalSaver
def get_saver() -> Saver:
    profile = os.getenv('profile', 'local')
    mapping = {
        'prod': CloudQueue(SQSClient('foo.com')),
        'local':LocalSaver()
    }
    return mapping.get(profile, LocalQueue())