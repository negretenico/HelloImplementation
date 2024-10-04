import boto3


class SQSClient:
    def __init__(self,url) -> None:
        self.sqs = boto3.client('sqs', region_name='us-east-1')
        self.queue_url = url
        
    def read_messages(self):
        response  = self.sqs.receive_message(
        QueueUrl=self.queue_url,
        MaxNumberOfMessages=1,  # Can fetch up to 10 messages at a time
        WaitTimeSeconds=5  # Long-polling to wait for up to 5 seconds for a message
        )
        return response.get('Message',[])
        
    def write_message(self, message_body):
        try:
            self.sqs.send_message(MessageBody=message_body)
            return "SUCCESS"
        except Exception:
            return "FAILURE"

    def queue_size(self):
        response = self.sqs.get_queue_attributes(
    QueueUrl=self.queue_url,
    AttributeNames=['ApproximateNumberOfMessages']  # Specify the attribute we want
    )
        return int(response['Attributes']['ApproximateNumberOfMessages'])
