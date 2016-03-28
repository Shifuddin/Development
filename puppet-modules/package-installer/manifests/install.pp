class installer::install inherits installer {
	package { $installer::install:
		ensure => 'installed' }
	package { $installer::install_custom:
		ensure => 'installed' }
	package { $installer::update:
		ensure => 'latest' }
}

