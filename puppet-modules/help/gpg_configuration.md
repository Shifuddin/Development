## By the end of the guide we will have:

### Prepared and published a repository signing key
A. Generate a Master Key
B. Generate a Subkey for Package Signing
C. Detach Master Key from Subkey

#### Generate a Master Key
1. To produce key we use GPG which requires some random data called entropy. To generate entropy on serve we install rng tool.
apt-get install rng-tools.

If you face any failing message just start rngd deamon manually
rngd -r /dev/urandom
2. Generate key
gpg --gen-key

During this process first select 1, enter keysize 4096, key validity unlimited, your name, email address and passphrase of the key. You will have a public key noted as pub. Please keep it for further use.

#### Generate a Subkey for Package Signing
We will generate another signing key from previously generated master key so that we dont need it at server.

1. gpg --edit-key pub
2. Please enter addkey
3. Your passphrase
4. Select 4, keysize and validity as before
5. Finally enter save

You will have two subkeys. Again save the subkey. In this case second one have S usage.

#### Detach Master Key From Subkey
1. gpg --export-secret-key pub > private.key
2. gpg --export pub >> private.key
3. gpg --delete-secret-key pub
4. gpg --import public.key signing.key
5. gpg --list-secret-keys

Please save second ssb for package signing. We will use this key to write distribution file of local repository.

#### Clean keys
rm public.key signing.key

#### Publish key
gpg --keyserver keyserver.ubuntu.com --send-key pub

** pub refers to first generated public key.
