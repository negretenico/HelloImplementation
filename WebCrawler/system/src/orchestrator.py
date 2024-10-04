from test._test_multiprocessing import get_queue
from src.crawl import crawl
from src.parse import parse
def orchestrator():
    frontier_queue = get_queue()
    parsing_queue = get_queue()
    while frontier_queue.size():
        to_enqueue = crawl(frontier_queue=frontier_queue)
        parsing_queue.enqueue(to_enqueue)
        parse(parsing_queue=parsing_queue)
