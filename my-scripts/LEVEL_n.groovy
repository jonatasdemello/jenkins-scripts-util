// Code snippet that extracts n from LEVEL_n (implemented as closure):

def level = { name ->
  def ret = 0
  name.eachMatch(~'LEVEL_([1-9]+[0-9*])', {ret = it[1].toInteger()})
  return ret
}
