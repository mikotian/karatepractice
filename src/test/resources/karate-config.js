function fn(){

var env = karate.env;

if(env == ''){
    env = 'qa'
}

var config={

      testUrl : 'https://jsonplaceholder.typicode.com'

}
return config;

}