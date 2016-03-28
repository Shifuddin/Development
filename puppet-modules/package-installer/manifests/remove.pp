class installer::remove inherits installer {
	package { $installer::purge:
		ensure => 'purged' }
}