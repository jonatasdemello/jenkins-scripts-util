/*
Groovy syntax
	https://code-maven.com/groovy-hello-world

*/

assert 'Groovy is cool!' == sprintf( '%2$s %3$s %1$s', ['cool!', 'Groovy', 'is'])
assert '00042' == sprintf('%05d', 42)

def x = 66
 
 
def res = sprintf("value: %s", x)   // as string
println(res)
println(sprintf("value: %d", x))    // as decimal
println(sprintf("value: %c", x))    // as character
 
 
//  padding with 0
printf('%05d\n', x)
println( sprintf('%05d', x)  )
 
 
// indicate location of the value
names = ['First', 'Second', 'Third', 'Fourt']
println( sprintf('%2$s %3$s %3$s %1$s', names) )
	
def v = 65
printf("<%s>\n", v);     // <65>
printf("<%10s>\n", v);   // <      65>
printf("<%-10s>\n", v);  // <65      >
printf("<%c>\n", v);     // <A>
printf("<%d>\n", v);     // <65>
printf("<%05d>\n", v);   // <00065>

