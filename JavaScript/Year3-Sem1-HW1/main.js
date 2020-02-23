const {
    Blockchain,
} = require('./blockchain');
const {
    CreateTx
} = require('./allTransaction');
const {
    MerkleTree
} = require('merkletreejs')
const {
    BloomFilter
} = require('bloom-filters')
const topology = require('fully-connected-topology')
const {
    stdin,
    exit,
    argv
} = process
const {
    log
} = console
const {
    me,
    peers
} = extractPeersAndMyPort()
const sockets = {}

log('---------------------')
log('Welcome to p2p chat!')
log('me - ', me)
log('peers - ', peers)
log('connecting to peers...')

const myIp = toLocalIp(me)
const peerIps = getPeerIps(peers)


const allTx = [];
const totalTree = [];
const filters = [];
const leaves = [];

const myChain = new Blockchain();
const createTx = new CreateTx();

flag = false;

createTx.crateAllTx(myChain, allTx);

for (i = 1; i <= myChain.chain.length - 1; i++) {
    leaves.length = 0;
    for (j = 0; j < myChain.chain[i].transactions.length; j++) {
        leaves.push(myChain.chain[i].transactions[j].hashTx);
    }
    totalTree.push(new MerkleTree(leaves, myChain.sha256));
}

//create bloom fiter
for (i = 1; i <= myChain.chain.length - 1; i++) {
    filters.push(new BloomFilter(20, 10));
}

//entering values to bloom filter
for (i = 1; i <= myChain.chain.length - 1; i++) {
    for (j = 0; j < myChain.chain[i].transactions.length; j++) {
        filters[i - 1].add(myChain.chain[i].transactions[j].hashTx);
    }
}

//connect to peers
topology(myIp, peerIps).on('connection', (socket, peerIp) => {
    const peerPort = extractPortFromIp(peerIp)
    log('connected to peer - ', peerPort)

    if (flag == true) {
        for (i = 0; i < totalTree.length; i++) {
            log("THE ROOT OF THE TREE THAT DESCRIBE BLOCK NUMBER " + (i + 1) + " : " + totalTree[i].getRoot().toString('hex'));
        }

        log('\n To check if a transaction appears on a particular block, enter its hash number\n');



        sockets[peerPort] = socket
        stdin.on('data', data => { //on user input
            const message = data.toString().trim()

            if (message === 'exit') { //on exit
                log('Bye bye')
                exit(0)
            } else {
                //first step : check if hash of this transaction found in some block with bloom filter
                for (i = 0; i < filters.length; i++) {
                    if (filters[i].has(message) == true) {
                        //if the answer is true - check for sure in this specific block is whether the transaction is there
                        const root = totalTree[i].getRoot().toString('hex');
                        const proof = totalTree[i].getProof(message);
                        console.log(totalTree[i].verify(proof, message, root));
                        break;
                    }
                    if (filters.length == i + 1) {
                        console.log(false);
                    }
                }
            }

            const receiverPeer = extractReceiverPeer(message)
            if (sockets[receiverPeer]) { //message to specific peer
                if (peerPort === receiverPeer) { //write only once
                    sockets[receiverPeer].write(formatMessage(extractMessageToSpecificPeer(message)))
                }
            } else { //broadcast message to everyone
                socket.write(formatMessage(message))
            }
        })

    }
    flag = true;

    //print data when received
    socket.on('data', data => log(data.toString('utf8')))
})


//extract ports from process arguments, {me: first_port, peers: rest... }
function extractPeersAndMyPort() {
    return {
        me: argv[2],
        peers: argv.slice(3, argv.length)
    }
}

//'4000' -> '127.0.0.1:4000'
function toLocalIp(port) {
    return `127.0.0.1:${port}`
}

//['4000', '4001'] -> ['127.0.0.1:4000', '127.0.0.1:4001']
function getPeerIps(peers) {
    return peers.map(peer => toLocalIp(peer))
}

//'hello' -> 'myPort:hello'
function formatMessage(message) {
    return `${me}>${message}`
}

//'127.0.0.1:4000' -> '4000'
function extractPortFromIp(peer) {
    return peer.toString().slice(peer.length - 4, peer.length);
}

//'4000>hello' -> '4000'
function extractReceiverPeer(message) {
    return message.slice(0, 4);
}

//'4000>hello' -> 'hello'
function extractMessageToSpecificPeer(message) {
    return message.slice(5, message.length);
}