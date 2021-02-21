package com.fks.kanban.infrastructure.security.authorization;


class PkceKeyGenerator {

    static void main(String[] args) {
        System.out.printf(PkceAuthorizationCodeTokenGranter.generateHashSha256("whereisjohngault#123@"));
    }

}
