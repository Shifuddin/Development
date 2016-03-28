class lpkgrepo::apache inherits lpkgrepo{

file{'/etc/apache2/sites-available/20-reporepo.conf':
ensure => 'present',
replace => 'yes',
content =>'<VirtualHost *:8080>
  ServerName default
  ServerAdmin root@localhost

  ## Vhost docroot
  DocumentRoot "/var/svrepo"

  ## Directories, there should at least be a declaration for /var/www

  <Directory "/var/svrepo">
    Options Indexes FollowSymLinks MultiViews
    AllowOverride None
    Require all granted
  </Directory>

  ## Logging
  ErrorLog "/var/log/apache2/default_error.log"
  ServerSignature Off
  CustomLog "/var/log/apache2/access.log" combined
  ## Script alias directives
  ScriptAlias /cgi-bin "/usr/lib/cgi-bin"
</VirtualHost>',
owner => 'root',
}


}
