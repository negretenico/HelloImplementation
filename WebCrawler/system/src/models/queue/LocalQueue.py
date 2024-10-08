import queue
from models.queue import QueueInterface

class LocalQueue(QueueInterface):
    def __init__(self) -> None:
        super().__init__()
        self.queue = queue()
    def dequeue(self):
        return self.queue

    def enqueue(self, item):
        return self.queue.put(item)

    def size(self):
        return self.queue.qsize()