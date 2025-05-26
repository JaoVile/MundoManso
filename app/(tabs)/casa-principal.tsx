import React, { useRef, useState } from 'react';
import {
  View,
  Text,
  ImageBackground,
  Animated,
  FlatList,
  TouchableOpacity,
  Image,
  StyleSheet,
  Dimensions,
} from 'react-native';
import { useRouter } from 'expo-router';


const { width } = Dimensions.get('window');

const filmes = [
   {
    id: '1',
  
    imagem: require('../../assets/videos/reino.png'),
  },

  {
    id: '2',
    
    imagem: require('../../assets/videos/O_PEQUENO_URSO.png'),
  },
 
  {
    id: '3',
    
    imagem: require('../../assets/videos/floresta.png'),
  },
];

export default function CasaScreen() {
  const scrollX = useRef(new Animated.Value(0)).current;
  const flatListRef = useRef<FlatList>(null);
  const [indexAtual, setIndexAtual] = useState(0);
  const router = useRouter();

  const irParaProximo = () => {
    if (indexAtual < filmes.length - 1) {
      flatListRef.current?.scrollToIndex({ index: indexAtual + 1, animated: true });
      setIndexAtual(indexAtual + 1);
    }
  };

  const irParaAnterior = () => {
    if (indexAtual > 0) {
      flatListRef.current?.scrollToIndex({ index: indexAtual - 1, animated: true });
      setIndexAtual(indexAtual - 1);
    }
  };

  return (
    <ImageBackground
      source={require('../../assets/images/fundo_filmes.jpg')}
      style={styles.background}
      resizeMode="cover"
    >

      <Animated.FlatList
        ref={flatListRef}
        data={filmes}
        horizontal
        pagingEnabled
        showsHorizontalScrollIndicator={false}
        keyExtractor={(item) => item.id}
        onScroll={Animated.event(
          [{ nativeEvent: { contentOffset: { x: scrollX } } }],
          { useNativeDriver: true }
        )}
        onMomentumScrollEnd={(event) => {
          const novoIndex = Math.round(event.nativeEvent.contentOffset.x / width);
          setIndexAtual(novoIndex);
        }}
        renderItem={({ item }) => (
          <TouchableOpacity
            style={styles.card}
            onPress={() => router.push('/detalhe')}
          >
            <Image source={item.imagem} style={styles.imagem} />
            <Text style={styles.nome}>{item.titulo}</Text>
          </TouchableOpacity>
        )}
      />

      <View style={styles.botoes}>
        <TouchableOpacity onPress={irParaAnterior} style={styles.botaoNavegar}>
          <Text style={styles.textoBotao}>{'◀'}</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={irParaProximo} style={styles.botaoNavegar}>
          <Text style={styles.textoBotao}>{'▶'}</Text>
        </TouchableOpacity>
      </View>

      <TouchableOpacity
        onPress={() => router.push('/')}
        style={styles.botaoVoltar}
      >
        <Text style={styles.textoVoltar}>Voltar para Home</Text>
      </TouchableOpacity>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: { flex: 1, paddingTop: 90 },
  card: {
    width,
    alignItems: 'center',
    padding: 1,
  },
  imagem: {
    width: width * 0.712,
    height: width * 1.04,
    borderRadius: 20,
    marginBottom:10,
  },
  nome: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#665544',
  },
  botoes: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginHorizontal: 95,
    marginTop: 20,
  },
  botaoNavegar: {
    backgroundColor: '#fff',
    padding: 25,
    borderRadius: 25,
    elevation: 5,
  },
  textoBotao: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  botaoVoltar: {
    marginTop: 70,
    marginBottom: 20,
    alignSelf: 'center',
    backgroundColor: '#fff',
    paddingHorizontal: 10,
    paddingVertical: 10,
    borderRadius: 15,
    elevation: 5,
  },
  textoVoltar: {
    fontSize: 15,
    color: '#665544',
  },
});
