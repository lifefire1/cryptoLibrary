# Elliptic Curve Cryptography Library and DH Protocol Implementation

Welcome to the project repository that includes an Elliptic Curve Cryptography (ECC) library and implementations of the Diffie-Hellman (DH) protocol and Elliptic Curve Diffie-Hellman (ECDH) protocol.

## DHEC Library

The **DHEC (Elliptic Curve Cryptography) Library** is a Java package designed to simplify working with elliptic curves in cryptography. It includes functionalities such as point operations, point multiplication, modular arithmetic, and inverse calculation.

Learn more about the DHEC Library in the [DHEC README](./src/DHEC/README.md).

## DH Protocol Implementation

The **Diffie-Hellman Protocol Implementation** includes both a client and a server component that demonstrate the establishment of a shared secret key using the DH protocol. This project allows two entities (Alice and Bob) to communicate securely by exchanging public keys and generating a shared secret key using the DH protocol.

Learn more about the DH Protocol Implementation in the [DH README](./src/DH/README.md).

## DHEC Protocol Implementation

The **Elliptic Curve Diffie-Hellman (ECDH) Protocol Implementation** is designed to securely exchange keys using elliptic curve cryptography. It includes a client and a server component that perform key exchange using elliptic curves.

### DHEC Client

The DHEC Client component generates a private key, calculates the public key, sends it to the server, and receives the server's public key. It then calculates the shared secret using elliptic curve operations.

Learn more about the DHEC Client in the [DHEC Client README](./src/DHEC/Client/README.md).

### DHEC Server

The DHEC Server component receives the client's public key, generates its own private and public keys, and sends the public key to the client. It then calculates the shared secret using elliptic curve operations.

Learn more about the DHEC Server in the [DHEC Server README](./DHEC/Server/README.md).

## Getting Started

To get started with the DHEC Library, DH Protocol Implementation, or DHEC Protocol Implementation, please refer to the respective README files in the subdirectories.

## Contribution

This project is open for contributions. If you have ideas for enhancements or bug fixes, feel free to contribute by opening an issue or a pull request on [GitHub](https://github.com/your/project-repo).

## License

The code in this repository is distributed under the [MIT License](./LICENSE), which means you can use, modify, and distribute it for both personal and commercial purposes.

Start building secure and efficient cryptographic applications using the DHEC Library, DH Protocol Implementation, and DHEC Protocol Implementation today!

---
*Note: This README provides an overview of the project components. Please refer to the individual README files within each subdirectory for specific details and instructions.*
