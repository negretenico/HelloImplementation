import boto3
class S3Client:
    def __init__(self):
        self.__client__ = boto3.client('s3')
        self.__bucket_name__ = "this_should_be_read_in"

    def save_file(self, file_name,content):
        self.__client__.put_object(Bucket=self.__bucket_name__,Key=file_name,Body=content)