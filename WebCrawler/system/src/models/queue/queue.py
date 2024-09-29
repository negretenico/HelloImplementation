from abc import abstractmethod,ABC
class QueueInterface(ABC):

    @abstractmethod
    def dequeue(self):
        pass

    @abstractmethod
    def enqueue(self, item):
        pass

    @abstractmethod
    def size(self):
        pass