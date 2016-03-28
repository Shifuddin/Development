# currentusers

#### Table of Contents

1. [Overview](#overview)
2. [Module Description - What the module does and why it is useful](#module-description)
3. [Setup - The basics of getting started with currentusers](#setup)
    * [What currentusers affects](#what-currentusers-affects)
    * [Setup requirements](#setup-requirements)
    * [Beginning with currentusers](#beginning-with-currentusers)
4. [Reference - An under-the-hood peek at what the module is doing and how](#reference)
5. [Limitations - OS compatibility, etc.](#limitations)
6. [Development - Guide for contributing to the module](#development)

## Overview

This module adds current users of a specific host to foreman web ui as a fact.

## Module Description

Foreman has various facts available for its users. But sometime we need to know who are currently utilizing machines, which is a missing facilities of foreman. This module comes into play in this scenario.

## Setup

### What currentusers affects

* puppet-currentusers add a new ruby file as facter so that foreman can load this file and show the fact value to its UI.
* Desired output will be available after two run. At first puppet run fact will be created and at second run output from that fact will be shown on UI.


### Setup Requirements **OPTIONAL**

You need to have facter installed at the machine higher than 1.7.

### Beginning with currentusers

Download the module from git lab repo. Enquiry the facter verion of your machine by facter -verion command and if it meets minimal requirement just install module normally and wait for two puppet run to see output.

## Reference

Here we have only one class named init.pp. Which ensures creation of new fact fi
le. File content is set so that we generate current users list where content is
a shell command.

## Limitations

Limited to facter version >= 1.7.

## Development

This module is developed by Md Shifuddin Al Masud <shifuddin.masud@gmail.com>. For further development or collaboration please contact.

## Release Notes/Contributors/Etc **Optional**

This is the initial release. Module is version controlled and version number is 0.1.0.
