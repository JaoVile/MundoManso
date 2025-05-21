import { View, TextInput, Button, StyleSheet, Image, ImageBackground } from 'react-native';

export default function LoginScreen() {
  return (
    <ImageBackground
      source={require('../../assets/images/background_login.png')}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.container}>
        {/* Logo imagem Mundo Manso */}
        <Image
          source={require('../../assets/images/Mundo_Manso_Nome.png')}
          style={styles.logo}
          resizeMode="contain"
        />

        <TextInput style={styles.input} placeholder="Seu Nome" placeholderTextColor="#999" />
        <TextInput
          style={styles.input}
          placeholder="Seu E-mail"
          keyboardType="email-address"
          placeholderTextColor="#999"
        />
        <TextInput
          style={styles.input}
          placeholder="Sua Senha"
          secureTextEntry
          placeholderTextColor="#999"
        />

        <View style={styles.buttonContainer}>
          <Button title="Entrar" onPress={() => { /* lógica de login */ }} color="#665544" />
        </View>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
  },
  logo: {
    width: '100%',
    height: 140,
    marginBottom: 20,
  },
  input: {
    height: 50,
    borderColor: '#665544',
    borderWidth: 2,
    marginBottom: 5,
    borderRadius: 20,
    paddingHorizontal: 12,
    backgroundColor: '#ffffffdd', 
  },
  buttonContainer: {
    marginTop: 10,
    borderRadius: 20,
    overflow: 'hidden',
  },
});
