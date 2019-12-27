def appendDoc(name, description, method, path, consumes, produces, output_example=""):
    res = f"**{name}**\n* *Description:* {description}\n* *Method:* `{method}`\n* *Path:* `{path}`\n* *Consumes:* `{consumes}`\n* *Produces:* `{produces}`\n"
    if output_example != "" and produces != "nothing":
        res += f"* *Output Example:* \n\n```\n{output_example}\n```"
    res += "\n"
    with open('documentazione.md', 'a') as out:
        out.write(res)


appendDoc(name="changePassword",
          description="cambia la password di un utente",
          method="PUT",
          path="/api/utenti/<idutente>/password",
          consumes="FormParam password: (String) nuova password",
          produces="nothing",
          output_example='''
{
    "id": 6,
    "email": "defendi.lombardi@gmail.com",
    "prov": "MN",
    "ruolo": "mb",
    "nome": "Defendi",
    "cognome": "Lombardi",
    "sesso": "M",
    "dataNascita": "1980-11-01 12:00:00.0",
    "luogoNascita": "Felonica",
    "codiceFiscale": "LMBDND80S01D529I",
    "idMedicoBase": 5
}
'''
        )



