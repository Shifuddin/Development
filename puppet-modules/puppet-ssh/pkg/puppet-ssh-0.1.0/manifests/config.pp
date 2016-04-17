class ssh::config inherits ssh
{
	file {'/root/.ssh/':
	      ensure => 'directory'
	}
	
	file {'/root/.ssh/authorized_keys':
	       ensure => 'present',	
	       mode   => '0600',
	       content => join ($ssh::authorized_keys, ","),
	       require => File['/root/.ssh/']
	}
	
}
