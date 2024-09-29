from cloud.SQSClient import SQSClient
from models.queue import QueueInterface


class CloudQueue(QueueInterface):

    def __init__(self,client:SQSClient):
        super().__init__()
        self.client = client
    def dequeue(self):
        return self.client.read_message("someUrl")
