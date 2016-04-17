# == Class: mount
#
# Full description of class mount here.
#
# === Parameters
#
# Document parameters here.
#
# [*sample_parameter*]
#   Explanation of what this parameter affects and what it defaults to.
#   e.g. "Specify one or more upstream ntp servers as an array."
#
# === Variables
#
# Here you should define a list of variables that this module would require.
#
# [*sample_variable*]
#   Explanation of how this variable affects the funtion of this class and if
#   it has a default. e.g. "The parameter enc_ntp_servers must be set by the
#   External Node Classifier as a comma separated list of hostnames." (Note,
#   global variables should be avoided in favor of class parameters as
#   of Puppet 2.6.)
#
# === Examples
#
#  class { 'mount':
#    servers => [ 'pool.ntp.org', 'ntp.local.company.com' ],
#  }
#
# === Authors
#
# Md Shifuddin Al Masud <author@domain.com>
#
# === Copyright
#
# Copyright 2016 Your name here, unless otherwise noted.
#
class mount 
(
	$auto_nfs = ['home -fstype=nfs,hard,intr,rw,rsize=8192,wsize=8192 nasls5:/srv/ls5/home', 'home_local -fstype=nfs,hard,intr,rw,rsize=8192 nasls5:/srv/ls5/home_local', 'maillist -fstype=nfs,hard,intr,rw,rsize=8192,wsize=8192 filerbg2:/export/mailservice/sccs/lists', 'www -fstype=nfs,hard,intr,rw,rsize=8192,wsize=8192 nasls5:/srv/ls5/www']
)
{

	file{'/etc/auto.master':
		ensure => 'present',
		content => "/import /etc/auto.nfs\r\n"
	}
	
	file {'/etc/auto.nfs':
		ensure => 'present',
		content =>join ($auto_nfs, "\r\n")
	}
}
