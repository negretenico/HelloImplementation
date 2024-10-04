import requests
from bs4 import BeautifulSoup
def fetch(url:str) -> str:
    res = requests.get(url)
    return BeautifulSoup(res.content,'html.parser')