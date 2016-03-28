class lpkgrepo::directory inherits lpkgrepo{
file{'/var/svrepo':
ensure =>'directory',
owner => 'root',
mode => '777',

}
file{'/var/svrepo/conf':
ensure =>'directory',
owner => 'root',
mode => '777',

}

file{'/var/svrepo/conf/distributions':
ensure =>'present',
owner => 'root',
content =>'Codename: trusty
Components: main
Architectures: i386 amd64
SignWith: 8B29E243
',
}

file{'/var/svrepo/conf/options':
ensure =>'present',
owner => 'root',
content => 'ask-passphrase
',
mode => '777',
}

}
