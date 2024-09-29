import boto3


class SQSClient:
    def __init__(self,url) -> None:
        self.sqs = boto3.client('sqs', region_name='us-east-1')
        self.queue_url = url
        
    def read_message(self):
        response  = self.sqs.receive_message(
        QueueUrl=self.queue_url,
        MaxNumberOfMessages=10,  # Can fetch up to 10 messages at a time
        WaitTimeSeconds=5  # Long-polling to wait for up to 5 seconds for a message
        )
        return response.get('Message',[])
        
    def write_message(self, message_body):
        try:
            self.sqs.send_message(MessageBody=message_body)
            return "SUCCESS"
        except Exception:
            return "FAILURE"
