from abc import abstractmethod,ABC
from src.models.saver.save_result import SaveResult


class Saver(ABC):

    @abstractmethod
    def save_file(self, file_name, content) -> SaveResult:
        pass