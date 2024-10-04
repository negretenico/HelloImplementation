from src.models.queue.queue import QueueInterface
from src.saver_mapper import get_saver


def parse(parsing_queue:QueueInterface):
    [file_name, content] = parsing_queue.dequeue()
    saver = get_saver()
    paragraphs = content.findall('p')
    saver.save_file(f"{file_name}-content", paragraphs)