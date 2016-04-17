# ssh

#### Table of Contents

1. [Overview](#overview)
2. [Module Description - What the module does and why it is useful](#module-description)
3. [Setup - The basics of getting started with ssh](#setup)
    * [What ssh affects](#what-ssh-affects)
    * [Setup requirements](#setup-requirements)
    * [Beginning with ssh](#beginning-with-ssh)
4. [Usage - Configuration options and additional functionality](#usage)
5. [Reference - An under-the-hood peek at what the module is doing and how](#reference)
5. [Limitations - OS compatibility, etc.](#limitations)
6. [Development - Guide for contributing to the module](#development)

## Overview

Install and configure ssh.

## Module Description

It has three puppet classes. Init puppet class responsible for calling other two classes and create smart variable 'authorized_keys'. Install puppet class installs ssh and makes it sure that ssh service is running. Finally config class configures ssh according to the value of smart variable.

## Setup

### What ssh affects

* Installs openssh-server.
* Run ssh service.
* Ensure ./ssh directory presents.

### Setup Requirements **OPTIONAL**

Puppet 3.8 or later versions.

### Beginning with ssh
Download mount from gitlab. Then install it on server. Finally attach the puppet class with the host machine.

## Limitations

No

## Development

Contact Md Shifuddin Al Masud <shifuddin.masud@gmail.com>

## Release Notes/Contributors/Etc **Optional**

Current version 0.1.0
