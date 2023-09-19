import requests
from bs4 import BeautifulSoup

target_url="https://news.ycombinator.com/"

def make_request(url):
    response=requests.get(url)
    soup=BeautifulSoup(response.text,"html.parser")
    return soup

def extract_title_rows(url):
    soup = make_request(url)
    title_rows = []
    
    title_elements = soup.find_all('td', class_='title')
    for title_element in title_elements:
        if title_element.find('span', class_='titleline'):
            title_rows.append(title_element.parent)
    return title_rows

def extract_titles(rows):
    news= []
    for row in rows:
        rank = row.find('span', class_='rank').get_text(strip=True)
        title_link =row.find('a', rel='noreferrer')
        if title_link:
            title = title_link.get_text(strip=True)
            news.append((rank, title))
    return news

def main():
    title_rows = extract_title_rows(target_url)
    news = extract_titles(title_rows)
    for new in news:
        print(new[0], new[1])

if __name__ == "__main__":
    main()