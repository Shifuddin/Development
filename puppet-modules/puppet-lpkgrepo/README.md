# lpkgrepo

#### Table of Contents

1. [Overview](#overview)
2. [Module Description - What the module does](#module-description)
3. [Setup - The basics of getting started with lpkgrepo](#setup)
    * [What lpkgrepo affects](#what-lpkgrepo-affects)
    * [Setup requirements](#setup-requirements)
    * [Beginning with lpkgrepo](#beginning-with-lpkgrepo)
4. [Usage - Configuration options and additional functionality](#usage)
5. [Reference - An under-the-hood peek at what the module is doing and how](#reference)
5. [Limitations - OS compatibility, etc.](#limitations)
6. [Development - Guide for contributing to the module](#development)

## Overview

It's useful to generate and maintain local repositories both for trusty and precise.

## Module Description

In many cases we need local repositories to install custom debian packages. This module lpkgrepo comes into play in those situations. It uses reprepro and apache server to configure local repositories. Finally it utilizes gpg key to sign the debian packages. 

It installs reprepro and apache2 it not installed already in the machine. Then configures local directory as local repository. In addition with this it also configures sites-avialable of apache2 and restart the service. 

## Setup

### What lpkgrepo affects

* Local filesystem where repository will be hosted like /var/svreo, apach2 configuration file and also apt source list are being altered by this module.

### Setup Requirements **OPTIONAL**

Before starting one should generate a key for package singing using gpg.

### Beginning with lpkgrepo

#### Install
sudo puppet module install -i /etc/puppet/environment/modules Mymodules/puppet-lpkgrepo/pkg/puppet-lpkgrepo-0.1.0.tar.gz
#### Add packages
Add new packages into newly manufactured repo using ... sudo reprepro -b /var/svrepo includedeb distributionName packageName
#### Provide key passphrase
You must provide authentic passphrase of public key to insert packages.
#### List of packages
sudo reprepo -b /var/svrepo list distributionName  

**distributionName refers to either trusty or precise for this release.
## Usage

Automate local repository creation with different distributions like trusty, precise. Also facilitates decorate packages according to different cpu architectures like arm, x86.

This module can be installed to any ubuntu machine and readily it will give feedback with a local repository at /var/svrepo to host packages.

## Reference

Puppet-lpkgrepo has four classes with specific tasks. 
### init.pp 
The main puppet class wich binds other class to perform desired task.
### apache.pp
Reponsible to ensure that apache2 is installed and has proper configuration to access this repository.
### directory.pp
Create directory to host packages. Also initiates distributions and option files to specify distribution name and its architecture.
### upaddress.pp
Downloads public key to veritfy packages and add repository address to apt source list.

## Limitations

In this version it's not possible to add distribution using foreman due to lack of loop functionality of pupppet.

## Development

If you like this module and wish to contribute to enhance this please contact with author Md Shifuddin Al Masud <shifuddin.masud@gmail.com>

## Release Notes/Contributors/Etc **Optional**

In this release we consider customize local repo for trusty and precise distributions. In both cases arm and x86 architecture facilities are given. 
