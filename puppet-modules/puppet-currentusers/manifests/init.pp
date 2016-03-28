# == Class: currentusers
#
# Full description of class currentusers here.
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
#  class { 'currentusers':
#    servers => [ 'pool.ntp.org', 'ntp.local.company.com' ],
#  }
#
# === Authors
#
# Author Md Shifuddin Al Masud <shifuddin.masud@gmail.com>
#
# === Copyright
#
# Copyright 2016 Shifuddin, unless otherwise noted.
#
class currentusers {

file{'/usr/lib/ruby/vendor_ruby/facter/current_users.rb':
ensure =>'present',
owner => 'root',
content =>'# current_users.rb

Facter.add(:current_users) do
setcode do
%x{/usr/bin/who | cut -d\' \' -f1,2 |sort |uniq | tr \' \' \',  \'| sed \'$s/.$//\'}.chomp
end
end',
}

}
