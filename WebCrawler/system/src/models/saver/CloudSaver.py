from models.saver.save_result import SaveResult
from src.models.saver.saver import Saver
from src.cloud import S3Client
import logging
class CloudSaver(Saver):

    def __init__(self, client:S3Client):
        super.__init__()
        self.client = client

    def save_file(self, file_name, content) -> SaveResult:
        try:
            self.client.save_file(file_name=file_name, content=content)
            return SaveResult.SUCCESS
        except Exception:
            logging.error(f"We failed to save the file {file_name}")
            return SaveResult.FAILURE