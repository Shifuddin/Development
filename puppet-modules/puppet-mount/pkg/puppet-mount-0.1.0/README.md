# mount

#### Table of Contents

1. [Overview](#overview)
2. [Module Description - What the module does and why it is useful](#module-description)
3. [Setup - The basics of getting started with mount](#setup)
    * [What mount affects](#what-mount-affects)
    * [Setup requirements](#setup-requirements)
    * [Beginning with mount](#beginning-with-mount)
4. [Usage - Configuration options and additional functionality](#usage)
5. [Reference - An under-the-hood peek at what the module is doing and how](#reference)
5. [Limitations - OS compatibility, etc.](#limitations)
6. [Development - Guide for contributing to the module](#development)

## Overview

This module mounts filesystem. 

## Module Description

In details, it moves ./auto.master and ./auto.nfs to /etc/auto.master and /etc/auto.nfs respectively after new installation of a machine.
## Setup

### What mount affects

* It just moves above mentioned directories to new place.
* Additionally move is performed if source directories are available.

### Setup Requirements **OPTIONAL**

You should have puppet 3.8 or later installed in your machine.

### Beginning with mount

Download mount from gitlab. Then install it on server. Finally attach the puppet class with the host machine.

## Reference

Here, only one puppet class used. In that move command used to serve the purpose if and only if source exists.

## Limitations

No.

## Development

Contact Md Shifuddin Al Masud <shifuddin.masud@gmail.com>

## Release Notes/Contributors/Etc **Optional**

Current version 0.1.0
