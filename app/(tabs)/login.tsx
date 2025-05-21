import { View, TextInput, Button, StyleSheet, Image, ImageBackground, Text, TouchableOpacity } from 'react-native';
import { useRouter } from 'expo-router';

export default function LoginScreen() {
  const router = useRouter();

  return (
    <ImageBackground
      source={require('../../assets/images/background_login.jpg')}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.container}>
        <Image
          source={require('../../assets/images/Mundo_Manso_Nome.png')}
          style={styles.logo}
          resizeMode="contain"
        />

        <TextInput style={styles.input} placeholder="Seu Nome" placeholderTextColor="#999" />
        <TextInput style={styles.input} placeholder="Sua Senha" secureTextEntry placeholderTextColor="#999" />

        <View style={styles.buttonContainer}>
          <Button title="Entrar" onPress={() => { /* lógica de login */ }} color="#665544" />
        </View>

        <TouchableOpacity onPress={() => router.push('/cadastro')}>
          <Text style={styles.link}>Criar uma conta</Text>
          <TouchableOpacity onPress={() => router.push('/')}>
          <Text style={styles.link}>Voltar para o início</Text>
          </TouchableOpacity>

        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: { flex: 1 },
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  logo: { width: '100%', height: 190, marginBottom: 30 },
  input: {
    height: 50,
    borderColor: '#665544',
    borderWidth: 2,
    marginBottom: 8,
    borderRadius: 20,
    paddingHorizontal: 12,
    backgroundColor: '#ffffffdd',
  },
  buttonContainer: { marginTop: 10, borderRadius: 20, overflow: 'hidden' },
  link: {
    marginTop: 20,
    textAlign: 'center',
    color: '#ffffffdd',
    textDecorationLine: 'underline',
    fontSize: 16,
  },
});
