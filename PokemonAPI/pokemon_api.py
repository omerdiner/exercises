import requests

url="https://pokeapi.co/api/v2/pokemon?limit=100"

response=requests.get(url)
result=response.json()

pokemon_url_list=[]

for pokemon in result["results"]:
    pokemon_url_list.append((pokemon["name"],pokemon["url"]))

for pokemon in pokemon_url_list:
    pokemon_response=requests.get(pokemon[1])
    pokemon_result=pokemon_response.json()
    abilities=pokemon_result["abilities"]
    print(pokemon[0].upper())
    for ability in abilities:
        ability_name = ability["ability"]["name"]
        is_hidden = ability["is_hidden"]
        hidden="Secret" if is_hidden else "Not secret"
        print(f"{ability_name:<20} = {hidden}")
    print("**************")



