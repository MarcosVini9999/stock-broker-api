const {createPool} = require('mysql')

const pool = createPool({
    host: 'localhost',
    user: 'root',
    password: 'senha123',
    connectionLimit: 50
})

module.exports = pool
