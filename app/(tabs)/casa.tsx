import { useRouter } from 'expo-router';
import {
  FlatList,
  Image,
  ImageBackground,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Dimensions,
} from 'react-native';


const filmes = [
  {
    id: '1',
    titulo: 'A Floresta Sonhadora',
    sinopse: 'Uma aventura mágica em uma floresta encantada onde criaturas mansas vivem em paz.',
    imagemCartaz: require('../../assets/images/Filme_O_Ursinho_Pooh.png'),
  },
  {
    id: '2',
    titulo: 'O Pequeno Urso',
    sinopse: 'O Pequeno Urso vive em uma cabana com seus pais, o Papai-Urso e a Mamãe-Urso, e passa seus dias explorando a floresta com seus amigos.',
    imagemCartaz: require('../../assets/images/Filme_irmão_Urso.png'),
  },
];

const { width } = Dimensions.get('window');

export default function Filme() {
  const router = useRouter();

  const renderItem = ({ item }: { item: typeof filmes[0] }) => (
    <View style={styles.container}>
      <Image source={item.imagemCartaz} style={styles.cartaz} />
      <Text style={styles.sinopseTitulo}>Sinopse:</Text>
      <Text style={styles.sinopse}>{item.sinopse}</Text>

      <TouchableOpacity
      onPress={() => {
        console.log('ID do botão:', item.id);
        router.replace(`/assistir?id=${item.id}`);
      }}>
      <Image
        source={require('../../assets/images/Botão_Assistir.png')}
        style={styles.botao_AS}
        />
        </TouchableOpacity>


      <TouchableOpacity onPress={() => router.push('/')}>
        <Image source={require('../../assets/images/Botão_Home.png')} style={styles.botaoHome} />
      </TouchableOpacity>
    </View>
  );

  return (
    <ImageBackground
      source={require('../../assets/images/Parte_de_tras_Filmes.png')}
      style={styles.background}
      resizeMode="cover"
    >
      <FlatList
        data={filmes}
        keyExtractor={(item) => item.id}
        renderItem={renderItem}
        horizontal
        pagingEnabled
        showsHorizontalScrollIndicator={false}
        snapToAlignment="center"
        decelerationRate="fast"
        getItemLayout={(_, index) => ({
          length: width,
          offset: width * index,
          index,
        })}
      />
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    width: '100%',
    height: '100%',
  },
  container: {
    width: width,
    padding: 20,
    alignItems: 'center',
    justifyContent: 'center',
  },
  cartaz: {
    width: 340,
    height: 400,
    borderRadius: 20,
    marginTop: 20,
  },
  sinopseTitulo: {
    width: 200,
    height: 21,
    fontFamily: 'Courier New',
    fontSize: 15,
    fontWeight: 'bold',
    color: '#000',
    marginTop: 3,
  },
  sinopse: {
    width: 300,
    height: 80,
    fontFamily: 'VT323',
    fontSize: 14,
    color: '#000',
    marginVertical: 10,
    textAlign: 'justify',
  },
  botao_AS: {
    height: 80,
    marginVertical: 10,
    resizeMode: 'contain',
  },
  botaoHome: {
    width: 120,
    height: 50,
    marginTop: 2,
    resizeMode: 'contain',
  },
});
