from src.queue_mapper import get_queue
from src.fetch import fetch
import logging
from src.saver_mapper import get_saver

import urllib.parse
import datetime
from src import ParsingQueue
from src.models.queue.queue import QueueInterface

def url_to_filename(url:str)->str:
    # Parse the URL
    parsed_url = urllib.parse.urlparse(url)
    
    domain = parsed_url.netloc.replace('.', '-')
    path = parsed_url.path.strip('/').replace('/', '_') 

    if not path:
        path = ""

    timestamp = datetime.datetime.now().strftime("%Y%m%d")
    
    return f"{domain}_{path}_{timestamp}.html"
    
    

def crawl(frontier_queue:QueueInterface):
    msg = frontier_queue.dequeue()
    try:
        soup = fetch(msg)
        saver = get_saver()
        file_name = url_to_filename(msg)
        saver.save_file(file_name=file_name, content=soup.text)
        return [file_name,soup]
    except Exception as e:
        logging.info(f"We could not fetch url {msg} with error {e}")

