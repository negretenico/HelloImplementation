from models.saver.save_result import SaveResult
from src.models.saver.saver import Saver
import logging

class LocalSaver(Saver):

    def __init__(self):
        super.__init__()
    
    def save_file(self, file_name, content) -> SaveResult:
        try:
            with open(file_name, 'w', encoding='utf-8') as file:
                file.write(content)
                return SaveResult.SUCCESS
        except Exception:
            logging.error(f"Error in save file {file_name}")
            return SaveResult.FAILURE

