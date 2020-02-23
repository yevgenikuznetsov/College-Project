const {
    Transaction,
} = require('./blockchain');

const EC = require('elliptic').ec;
const ec = new EC('secp256k1');

const firstUserKey = ec.genKeyPair();
const secondUserKey = ec.genKeyPair();
const thirdUserKey = ec.genKeyPair();

class CreateTx {

    crateAllTx(blockChain, txArray) {

        //TRANSACTION NUM 1
        const tx1 = new Transaction(firstUserKey.getPublic('hex'), '2', 100);
        tx1.signTransaction(firstUserKey);
        txArray.push(tx1);
        blockChain.addTransaction(tx1);

        //TRANSACTION NUM 2
        const tx2 = new Transaction(firstUserKey.getPublic('hex'), '3', 100);
        tx2.signTransaction(firstUserKey);
        txArray.push(tx2);
        blockChain.addTransaction(tx2);

        //TRANSACTION NUM 3
        const tx3 = new Transaction(firstUserKey.getPublic('hex'), '2', 50);
        tx3.signTransaction(firstUserKey);
        txArray.push(tx3);
        blockChain.addTransaction(tx3);

        //TRANSACTION NUM 4
        const tx4 = new Transaction(firstUserKey.getPublic('hex'), '3', 20);
        tx4.signTransaction(firstUserKey);
        txArray.push(tx4);
        blockChain.addTransaction(tx4);

        //CREATE BLOCK WITH 4 TRANSACTION
        blockChain.minePendingTransactions(firstUserKey.getPublic('hex'));


        //TRANSACTION NUM 5
        const tx5 = new Transaction(secondUserKey.getPublic('hex'), '3', 20);
        tx5.signTransaction(secondUserKey);
        txArray.push(tx5);
        blockChain.addTransaction(tx5);

        //TRANSACTION NUM 6
        const tx6 = new Transaction(secondUserKey.getPublic('hex'), '1', 150);
        tx6.signTransaction(secondUserKey);
        txArray.push(tx6);
        blockChain.addTransaction(tx6);

        //TRANSACTION NUM 7
        const tx7 = new Transaction(secondUserKey.getPublic('hex'), '3', 10);
        tx7.signTransaction(secondUserKey);
        txArray.push(tx7);
        blockChain.addTransaction(tx7);

        //TRANSACTION NUM 8
        const tx8 = new Transaction(secondUserKey.getPublic('hex'), '1', 10);
        tx8.signTransaction(secondUserKey);
        txArray.push(tx8);
        blockChain.addTransaction(tx8);

        //CREATE BLOCK WITH 4 TRANSACTION
        blockChain.minePendingTransactions(secondUserKey.getPublic('hex'));


        //TRANSACTION NUM 9
        const tx9 = new Transaction(thirdUserKey.getPublic('hex'), '1', 150);
        tx9.signTransaction(thirdUserKey);
        txArray.push(tx9);
        blockChain.addTransaction(tx9);

        //TRANSACTION NUM 10
        const tx10 = new Transaction(thirdUserKey.getPublic('hex'), '2', 50);
        tx10.signTransaction(thirdUserKey);
        txArray.push(tx10);
        blockChain.addTransaction(tx10);

        //TRANSACTION NUM 11
        const tx11 = new Transaction(thirdUserKey.getPublic('hex'), '2', 15);
        tx11.signTransaction(thirdUserKey);
        txArray.push(tx11);
        blockChain.addTransaction(tx11);

        //TRANSACTION NUM 12
        const tx12 = new Transaction(thirdUserKey.getPublic('hex'), '1', 20);
        tx12.signTransaction(thirdUserKey);
        txArray.push(tx12);
        blockChain.addTransaction(tx12);

        //CREATE BLOCK WITH 4 TRANSACTION
        blockChain.minePendingTransactions(thirdUserKey.getPublic('hex'));

        //TRANSACTION NUM 13
        const tx13 = new Transaction(firstUserKey.getPublic('hex'), '2', 20);
        tx13.signTransaction(firstUserKey);
        txArray.push(tx13);
        blockChain.addTransaction(tx13);

        //TRANSACTION NUM 14
        const tx14 = new Transaction(firstUserKey.getPublic('hex'), '3', 70);
        tx14.signTransaction(firstUserKey);
        txArray.push(tx14);
        blockChain.addTransaction(tx14);

        //TRANSACTION NUM 15
        const tx15 = new Transaction(firstUserKey.getPublic('hex'), '3', 50);
        tx15.signTransaction(firstUserKey);
        txArray.push(tx15);
        blockChain.addTransaction(tx15);

        //TRANSACTION NUM 16
        const tx16 = new Transaction(firstUserKey.getPublic('hex'), '2', 60);
        tx16.signTransaction(firstUserKey);
        txArray.push(tx16);
        blockChain.addTransaction(tx16);

        //CREATE BLOCK WITH 4 TRANSACTION
        blockChain.minePendingTransactions(firstUserKey.getPublic('hex'));

        //TRANSACTION NUM 17
        const tx17 = new Transaction(secondUserKey.getPublic('hex'), '1', 80);
        tx17.signTransaction(secondUserKey);
        txArray.push(tx17);
        blockChain.addTransaction(tx17);

        //TRANSACTION NUM 18
        const tx18 = new Transaction(thirdUserKey.getPublic('hex'), '1', 80);
        tx18.signTransaction(thirdUserKey);
        txArray.push(tx18);
        blockChain.addTransaction(tx18);

    }





}

module.exports.CreateTx = CreateTx;