from src.models.saver.saver import Saver
import os
from src.models.saver import LocalSaver
from src.models.saver.CloudSaver import CloudSaver
from src.cloud.S3Client import S3Client
def get_saver() -> Saver:
    profile = os.getenv('profile', 'local')
    mapping = {
        'prod': CloudSaver(S3Client()),
        'local':LocalSaver()
    }
    return mapping.get(profile, LocalSaver())